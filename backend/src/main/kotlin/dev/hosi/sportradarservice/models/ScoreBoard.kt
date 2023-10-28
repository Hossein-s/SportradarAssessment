package dev.hosi.sportradarservice.models

import org.springframework.stereotype.Component

@Component
class ScoreBoard {
    private val matches = LinkedHashMap<Int, Match>()
    private var counter = 0
    private val nextId = { counter++ }

    fun createMatch(homeTeam: String, awayTeam: String): Match {
        val id = nextId()
        val match = Match(id, homeTeam, awayTeam)

        matches[id] = match
        return match
    }

    fun updateMatch(id: Int, homeScore: Int, awayScore: Int) {
        val match = matches[id] ?: throw IllegalArgumentException("Invalid match id")
        match.updateScore(homeScore, awayScore)
    }

    fun finishMatch(id: Int) {
        matches.remove(id) ?: throw IllegalArgumentException("Invalid match id")
    }

    fun getMatches(): List<Match> {
        return matches
            .values
            .reversed()
            .sortedByDescending { it.homeScore + it.awayScore }
    }
}
