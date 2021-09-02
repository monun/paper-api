package io.github.monun.paperapi

import org.apache.commons.cli.*
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val options = Options()

    Option("r", "request", true, "Action to be requested (version, build, download)").apply {
        isRequired = true
    }.also {
        options += it
    }

    Option("v", "version", true, null).apply {
        isRequired = false
    }.also {
        options += it
    }

    Option("b", "build", true, null).apply {
        isRequired = false
    }.also {
        options += it
    }

    val formatter = HelpFormatter()

    if (args.isEmpty()) {
        formatter.printHelp("paper-api", options)
        exitProcess(1)
    }

    val cmd = runCatching {
        val parser = DefaultParser()
        parser.parse(options, args)
    }.onFailure { exception ->
        println(exception.message)
        formatter.printHelp("paper-api", options)
        exitProcess(1)
    }.getOrThrow()

    if (!execute(cmd)) {
        formatter.printHelp("paper-api", options)
    }
}

private fun execute(cmd: CommandLine): Boolean {
    val request = cmd.getOptionValue("request")
    val version = cmd.getOptionValue("version")
    var build = cmd.getOptionValue("build")

    if (build != null && version != null && build.equals("latest", true)) {
        build = PaperAPI.latestBuild(version)
    }

    when (request.lowercase()) {
        "version" -> {
            requireNotNull(version) {"version request requires a version"}

            PaperAPI.version(version)
        }
        "build" -> {
            requireNotNull(version) {"build request requires a version"}
            requireNotNull(build) {"build request requires a build"}

            PaperAPI.build(version, build)
        }
        "download" -> {
            requireNotNull(version) {"download request requires a version"}
            requireNotNull(build) {"download request requires a build"}

            PaperAPI.download(version, build)
        }
        else -> return false
    }

    return true
}

private operator fun Options.plusAssign(option: Option) {
    addOption(option)
}
