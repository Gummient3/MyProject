import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

data class Tokens(val accessToken: String, val refreshToken: String)

object JwtProvider {
    private const val SECRET = "your-secret"
    private const val ISSUER = "your-issuer"
    private const val ACCESS_TOKEN_EXPIRES_IN = 15 * 60 * 1000L // 15 минут
    private const val REFRESH_TOKEN_EXPIRES_IN = 7 * 24 * 60 * 60 * 1000L // 7 дней

    private val algorithm = Algorithm.HMAC256(SECRET)

    fun createAccessToken(userId: String): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(userId)
            .withExpiresAt(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRES_IN))
            .sign(algorithm)
    }

    fun createRefreshToken(userId: String): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(userId)
            .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRES_IN))
            .sign(algorithm)
    }

    fun verifyToken(token: String): Boolean {
        return try {
            JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
            true
        } catch (exception: Exception) {
            false
        }
    }

    fun refreshToken(refreshToken: String): Tokens? {
        return if (verifyToken(refreshToken)) {
            val userId = JWT.decode(refreshToken).subject
            Tokens(createAccessToken(userId), createRefreshToken(userId))
        } else {
            null
        }
    }
}

fun main() {
    val userId = "user-123"
    val tokens = Tokens(
        accessToken = JwtProvider.createAccessToken(userId),
        refreshToken = JwtProvider.createRefreshToken(userId)
    )

    println("Access Token: ${tokens.accessToken}")
    println("Refresh Token: ${tokens.refreshToken}")

    // ВАЛИДАЦИЯ ТОКЕНА
    println("Access Token valid: ${JwtProvider.verifyToken(tokens.accessToken)}")
    println("Refresh Token valid: ${JwtProvider.verifyToken(tokens.refreshToken)}")

    // ОБНОВЛЕНИЕ ТОКЕНА
    val newTokens = JwtProvider.refreshToken(tokens.refreshToken)
    if (newTokens != null) {
        println("New Access Token: ${newTokens.accessToken}")
        println("New Refresh Token: ${newTokens.refreshToken}")
    } else {
        println("Invalid Refresh Token")
    }
}
