package dev.drewhamilton.poko

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(CommandLineProcessor::class)
@ExperimentalCompilerApi
class PokoCommandLineProcessor : CommandLineProcessor {

    override val pluginId = ArtifactInfo.COMPILER_PLUGIN_ARTIFACT

    override val pluginOptions: Collection<AbstractCliOption> = listOf(
        CliOption(CompilerOptions.ENABLED.toString(), "<true|false>", "", required = true),
        CliOption(CompilerOptions.POKO_ANNOTATION.toString(), "Annotation class name", "", required = true),
    )

    override fun processOption(
        option: AbstractCliOption,
        value: String,
        configuration: CompilerConfiguration
    ) = when (option.optionName) {
        CompilerOptions.ENABLED.toString() -> configuration.put(CompilerOptions.ENABLED, value.toBoolean())
        CompilerOptions.POKO_ANNOTATION.toString() -> configuration.put(CompilerOptions.POKO_ANNOTATION, value)
        else -> throw IllegalArgumentException("Unknown plugin option: ${option.optionName}")
    }
}
