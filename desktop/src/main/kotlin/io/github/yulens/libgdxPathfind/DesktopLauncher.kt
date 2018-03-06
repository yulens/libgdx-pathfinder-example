package io.github.yulens.libgdxPathfind

import com.badlogic.gdx.Files.FileType
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

/** Launches the desktop (LWJGL) application.  */
object DesktopLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication(): LwjglApplication {
        val applicationConfig = LwjglApplicationConfiguration().apply {
            title = "libgdx-pathfind-example"
            width = 1024
            height = 768
            for (size in intArrayOf(128, 64, 32, 16)) {
                addIcon("libgdx$size.png", FileType.Internal)
            }
        }
        return LwjglApplication(io.github.yulens.libgdxPathfind.MainGame(), applicationConfig)
    }
}