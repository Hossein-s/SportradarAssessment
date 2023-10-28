package dev.hosi.sportradarservice.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant

class MatchTest {
    @Test
    fun `should create new match with 0-0 score`() {
        val match = Match(1, "Germany", "Spain")

        assertEquals(0, match.homeScore)
        assertEquals(0, match.awayScore)
    }

    @Test
    fun `should update match score`() {
        val match = Match(1, "Germany", "Spain")

        match.updateScore(3, 2)

        assertEquals(3, match.homeScore)
        assertEquals(2, match.awayScore)
    }
}
