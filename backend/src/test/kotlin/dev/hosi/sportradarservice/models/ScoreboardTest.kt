package dev.hosi.sportradarservice.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ScoreboardTest() {
    @Test
    fun `should create first match with id 1 and 0-0 score`() {
        val scoreboard = Scoreboard()
        assertEquals(0, scoreboard.getMatches().size)

        val match = scoreboard.createMatch("Germany", "Spain")

        assertEquals(1, match.id)
        assertEquals(0, match.homeScore)
        assertEquals(0, match.awayScore)
        assertEquals(1, scoreboard.getMatches().size)
    }

    @Test
    fun `should update first match score`() {
        val scoreboard = Scoreboard()
        val match = scoreboard.createMatch("Germany", "Spain")

        val updated = scoreboard.updateMatch(match.id, 3, 3)

        assertEquals(3, updated.homeScore)
        assertEquals(3, updated.awayScore)
    }

    @Test
    fun `should remove match`() {
        val scoreboard = Scoreboard()
        val match = scoreboard.createMatch("Germany", "Spain")

        scoreboard.finishMatch(match.id)

        assertEquals(0, scoreboard.getMatches().size)
    }

    @Test
    fun `should throw exception if match id not defined when finishing`() {
        val scoreboard = Scoreboard()

        assertThrows<IllegalArgumentException> {
            scoreboard.finishMatch(2)
        }
    }

    @Test
    fun `should return summary in descending order`() {
        val scoreboard = Scoreboard()
        val m1 = scoreboard.createMatch("Germany", "Spain")
        val m2 = scoreboard.createMatch("Portugal", "Croatia")
        val m3 = scoreboard.createMatch("France", "Italy")
        val m4 = scoreboard.createMatch("Argentina", "Brazil")
        val m5 = scoreboard.createMatch("Netherlands", "England")

        val mu1 = scoreboard.updateMatch(m1.id, 3, 2)
        val mu2 = scoreboard.updateMatch(m2.id, 4, 3)
        val mu3 = scoreboard.updateMatch(m3.id, 1, 4)
        val mu4 = scoreboard.updateMatch(m4.id, 5, 0)
        val mu5 = scoreboard.updateMatch(m5.id, 2, 1)

        val expected = arrayOf(mu2, mu4, mu3, mu1, mu5)

        assert(
            scoreboard.getMatches().withIndex().all { (id, match) ->
                expected[id] == match
            }
        )
    }
}
