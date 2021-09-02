package io.github.monun.paperapi

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request

object PaperAPI {
    private const val paperAPI = "https://papermc.io/api/v2/projects/paper"
    private val client = OkHttpClient()

    private fun infoVersion(version: String): JsonObject {
        val request = Request.Builder().apply {
            header("accept", "application/json")
            url("$paperAPI/versions/$version")
        }.build()

        client.newCall(request).execute().use { response ->
            return JsonParser.parseString(response.body?.string()) as JsonObject
        }
    }

    fun version(version: String) {
        val response = infoVersion(version)
        val builds = response.getAsJsonArray("builds")

        for (build in builds) {
            println(build.asInt)
        }
    }

    private fun infoBuild(version: String, build: String): JsonObject {
        Request.Builder().apply {
            header("accept", "application/json")
            url("$paperAPI/versions/$version/builds/$build")
        }.build().let { request ->
            client.newCall(request).execute().use { response ->
                return JsonParser.parseString(response.body?.string()) as JsonObject
            }
        }
    }

    fun latestBuild(version: String): String {
        val versionInfo = infoVersion(version)
        if (!versionInfo.has("builds")) error(versionInfo.toString())
        return versionInfo.getAsJsonArray("builds").last().asString
    }

    fun build(version: String, build: String) {
        val response = infoBuild(version, build)
        println(response)
        val application = response["downloads"].asJsonObject["application"].asJsonObject

        println("time=${response.get("time").asString}")
        println("commit=${response.getAsJsonArray("changes").first().asJsonObject.get("commit").asString}")
        println("jar=${application["name"].asString}")
        println("sha256=${application["sha256"].asString}")
    }

    fun download(version: String, build: String) {
        val buildInfo = infoBuild(version, build)
        if (buildInfo.has("error")) error(buildInfo.toString())

        val download =
            buildInfo.getAsJsonObject("downloads").getAsJsonObject("application").get("name").asString

        println("$paperAPI/versions/$version/builds/$build/downloads/$download")
    }
}