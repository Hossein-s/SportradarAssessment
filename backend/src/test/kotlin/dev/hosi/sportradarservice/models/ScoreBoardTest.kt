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

        scoreBoard.updateMatch(match.id, 3, 3)

        assertEquals(3, match.homeScore)
        assertEquals(3, match.awayScore)
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
        val match1 = scoreBoard.createMatch("Germany", "Spain")
        val match2 = scoreBoard.createMatch("Portugal", "Croatia")
        val match3 = scoreBoard.createMatch("France", "Italy")
        val match4 = scoreBoard.createMatch("Argentina", "Brazil")
        val match5 = scoreBoard.createMatch("Netherlands", "England")

        scoreBoard.updateMatch(match1.id, 3, 2)
        scoreBoard.updateMatch(match2.id, 4, 3)
        scoreBoard.updateMatch(match3.id, 1, 4)
        scoreBoard.updateMatch(match4.id, 5, 0)
        scoreBoard.updateMatch(match5.id, 2, 1)

        val expected = arrayOf(match2, match4, match3, match1, match5)

        assert(
            scoreBoard.getMatches().withIndex().all { (id, match) ->
                expected[id] == match
            }
        )
    }
}
