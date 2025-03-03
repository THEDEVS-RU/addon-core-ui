package dev.qubik.autoconfigure.coreui;

import dev.qubik.coreui.CoreuiConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({CoreuiConfiguration.class})
public class CoreuiAutoConfiguration {
}

