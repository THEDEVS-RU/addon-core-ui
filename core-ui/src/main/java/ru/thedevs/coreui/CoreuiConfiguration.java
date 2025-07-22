package ru.thedevs.coreui;

import io.jmix.chartsflowui.ChartsFlowuiConfiguration;
import io.jmix.core.annotation.JmixModule;
import io.jmix.core.impl.scanning.AnnotationScanMetadataReaderFactory;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.flowui.FlowuiConfiguration;
import io.jmix.flowui.sys.ActionsConfiguration;
import io.jmix.flowui.sys.ViewControllersConfiguration;
import io.jmix.gridexportflowui.GridExportFlowuiConfiguration;
import io.jmix.multitenancyflowui.MultitenancyFlowuiConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.thedevs.CoreConfiguration;
import java.util.Collections;

@Configuration
@ComponentScan
@ConfigurationPropertiesScan
@JmixModule(dependsOn = {EclipselinkConfiguration.class,
        FlowuiConfiguration.class,
        CoreConfiguration.class,
        ChartsFlowuiConfiguration.class,
        GridExportFlowuiConfiguration.class,
        MultitenancyFlowuiConfiguration.class})
@PropertySource(name = "ru.thedevs.coreui", value = "classpath:/ru/thedevs/coreui/module.properties")
public class CoreuiConfiguration {

    @Bean("coreui_CoreuiViewControllers")
    public ViewControllersConfiguration screens(final ApplicationContext applicationContext,
                                                final AnnotationScanMetadataReaderFactory metadataReaderFactory) {
        final ViewControllersConfiguration viewControllers
                = new ViewControllersConfiguration(applicationContext, metadataReaderFactory);
        viewControllers.setBasePackages(Collections.singletonList("ru.thedevs.coreui"));
        return viewControllers;
    }

    @Bean("coreui_CoreuiActions")
    public ActionsConfiguration actions(final ApplicationContext applicationContext,
                                        final AnnotationScanMetadataReaderFactory metadataReaderFactory) {
        final ActionsConfiguration actions
                = new ActionsConfiguration(applicationContext, metadataReaderFactory);
        actions.setBasePackages(Collections.singletonList("ru.thedevs.coreui"));
        return actions;
    }
}
