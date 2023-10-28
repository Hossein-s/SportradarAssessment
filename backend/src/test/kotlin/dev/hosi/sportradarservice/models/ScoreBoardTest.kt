package dev.hosi.sportradarservice.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ScoreBoardTest() {
    @Test
    fun `should create first match with id 1 and 0-0 score`() {
        val scoreBoard = ScoreBoard()
        assertEquals(0, scoreBoard.getMatches().size)

        val match = scoreBoard.createMatch("Germany", "Spain")

        assertEquals(0, match.homeScore)
        assertEquals(0, match.awayScore)
        assertEquals(1, scoreBoard.getMatches().size)
    }

    @Test
    fun `should update first match score`() {
        val scoreBoard = ScoreBoard()
        val match = scoreBoard.createMatch("Germany", "Spain")

        val updated = scoreBoard.updateMatch(match.id, 3, 3)

        assertEquals(3, updated.homeScore)
        assertEquals(3, updated.awayScore)
    }

    @Test
    fun `should remove match`() {
        val scoreBoard = ScoreBoard()
        val match = scoreBoard.createMatch("Germany", "Spain")

        scoreBoard.finishMatch(match.id)

        assertEquals(0, scoreBoard.getMatches().size)
    }

    @Test
    fun `should throw exception if match id not defined when finishing`() {
        val scoreBoard = ScoreBoard()

        assertThrows<IllegalArgumentException> {
            scoreBoard.finishMatch(2)
        }
    }

    @Test
    fun `should return summary in descending order`() {
        val scoreBoard = ScoreBoard()
        val m1 = scoreBoard.createMatch("Germany", "Spain")
        val m2 = scoreBoard.createMatch("Portugal", "Croatia")
        val m3 = scoreBoard.createMatch("France", "Italy")
        val m4 = scoreBoard.createMatch("Argentina", "Brazil")
        val m5 = scoreBoard.createMatch("Netherlands", "England")

        val mu1 = scoreBoard.updateMatch(m1.id, 3, 2)
        val mu2 = scoreBoard.updateMatch(m2.id, 4, 3)
        val mu3 = scoreBoard.updateMatch(m3.id, 1, 4)
        val mu4 = scoreBoard.updateMatch(m4.id, 5, 0)
        val mu5 = scoreBoard.updateMatch(m5.id, 2, 1)

        val expected = arrayOf(mu2, mu4, mu3, mu1, mu5)

        assert(
            scoreBoard.getMatches().withIndex().all { (id, match) ->
                expected[id] == match
            }
        )
    }
}
