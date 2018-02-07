package com.rustedshark.mud.commands;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.rustedshark.mud.data.game.GameSession;
import com.rustedshark.mud.injection.RuntimeInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controls and manages all of the commands for the Mud
 */
@Component
public class CommandManager {

    private static final Logger logger = LoggerFactory.getLogger(CommandManager.class);

    private static final HashMap<String, Class<? extends AbstractCommand>> mudCommands = new HashMap<>();
    static {
        findClasses(MudCommand.class.getPackage().getName(), MudCommand.class);
    }

    @Inject
    private RuntimeInjector injector;

    private static ClassPathScanningCandidateComponentProvider createScanner(Class<? extends Annotation> annotation) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(annotation));
        return provider;
    }

    public static List<Class<?>> findClasses(String topLevelPackage, Class<? extends Annotation> annotation) {

        Class<AbstractCommand> requiredSubtype = AbstractCommand.class;

        return createScanner(annotation).findCandidateComponents(topLevelPackage).stream().map(beanDefinition -> {
            String commandClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> clazz = Class.forName(commandClassName);
                if(clazz.isAssignableFrom(requiredSubtype)) {
                    logger.info("CommandManager: Found command type \"{}\"");
                    return clazz;
                } else {
                    logger.warn("CommandManager: Found command \"{}\" however determined it was not compatible with {}", requiredSubtype);
                }
            } catch (Exception e) {
                logger.warn("CommandManager: Failed to auto-locate command by the name of \"{}\"", commandClassName);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public CommandManager addCommands(List<Class<? extends AbstractCommand>> commands) {
        synchronized (mudCommands) {
            commands.forEach(command -> {
                String commandName = command.getSimpleName();
                mudCommands.put(commandName, command);
            });
        }
        return this;
    }

    public CommandBuilder createBuilder(String input) {
        return new CommandBuilder(input);
    }

    public class CommandBuilder {
        private final String commandName;
        private final String[] commandOptions;
        private GameSession gameSession;

        private CommandBuilder(String input) {
            List<String> commandInputs = Splitter.on(CharMatcher.whitespace()).trimResults().omitEmptyStrings().splitToList(input);
            if(commandInputs.size() > 0) {
                commandName = commandInputs.get(0);
                commandInputs.remove(0);
                commandOptions = commandInputs.toArray(new String[commandInputs.size()]);
            } else {
                commandName = "";
                commandOptions = new String[0];
            }
        }

        public CommandBuilder withSession(GameSession session) {
            this.gameSession = session;
            return this;
        }

        public Optional<AbstractCommand> buildCommand() {
            Class<? extends AbstractCommand> clazz = mudCommands.get(commandName);
            try {
                AbstractCommand command = clazz.getConstructor(GameSession.class).newInstance(gameSession);
                return Optional.of(command);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                logger.error("CommandBuilder[sid={}]: Failed to construct known command \"{}\"", gameSession.getId(), commandName, e);
            }
            return Optional.empty();
        }

        /**
         * Returns the friendly name of the command
         * @return {@link String}
         */
        public String getCommandName() {
            return commandName;
        }

        /**
         * Returns back an immutable set of options
         * @return {@link String[]}
         */
        public String[] getCommandOptions() {
            return Arrays.copyOf(commandOptions, commandOptions.length);
        }
    }

}
