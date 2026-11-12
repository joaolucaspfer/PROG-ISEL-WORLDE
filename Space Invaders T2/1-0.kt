import pt.isel.canvas.*

const val WIDTH = 600
const val HEIGHT = 600

const val PLAYER_SIZE = 50
const val PLAYER_Y = HEIGHT - 4 * PLAYER_SIZE

fun main() {
    var playerX = WIDTH / 3 - PLAYER_SIZE / 2

    onStart {
        val cv = Canvas(WIDTH, HEIGHT, BLACK)

        cv.drawRect(playerX, PLAYER_Y, PLAYER_SIZE, PLAYER_SIZE, BLUE)

        cv.onMouseMove { mouseEvent ->
            playerX = mouseEvent.x - PLAYER_SIZE / 2

            if (playerX < 0) playerX = 0
            if (playerX > WIDTH - PLAYER_SIZE) playerX = WIDTH - PLAYER_SIZE

            cv.erase()
            cv.drawRect(playerX, PLAYER_Y, PLAYER_SIZE, PLAYER_SIZE, BLUE)
        }
    }
}