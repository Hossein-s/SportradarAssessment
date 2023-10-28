package dev.hosi.sportradarservice.models

import dev.hosi.sportradarservice.dtos.MatchOutput
import org.springframework.stereotype.Component

@Component
class Scoreboard {
    private val matches = LinkedHashMap<Int, Match>()
    private var counter = 0
    private val nextId = { counter++ }

    fun createMatch(homeTeam: String, awayTeam: String): MatchOutput {
        val id = nextId()
        val match = Match(id, homeTeam, awayTeam)

        matches[id] = match

        return MatchOutput.fromMatch(match)
    }

    fun getMatch(id: Int): MatchOutput {
        return matches[id]?.let { MatchOutput.fromMatch(it) } ?: throw IllegalArgumentException("Invalid match id")
    }

    fun updateMatch(id: Int, homeScore: Int, awayScore: Int): MatchOutput {
        val match = matches[id] ?: throw IllegalArgumentException("Invalid match id")
        match.updateScore(homeScore, awayScore)

        return MatchOutput.fromMatch(match)
    }

    fun finishMatch(id: Int) {
        matches.remove(id) ?: throw IllegalArgumentException("Invalid match id")
    }

    fun getMatches(): List<MatchOutput> {
        return matches
            .values
            .reversed()
            .sortedByDescending { it.homeScore + it.awayScore }
            .map { MatchOutput.fromMatch(it) }
    }
}
