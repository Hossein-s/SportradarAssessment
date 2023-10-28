package dev.hosi.sportradarservice.dtos

import dev.hosi.sportradarservice.models.Match

data class MatchOutput(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
) {
    companion object {
        fun fromMatch(match: Match): MatchOutput {
            return MatchOutput(
                match.id,
                match.homeTeam,
                match.awayTeam,
                match.homeScore,
                match.awayScore
            )
        }
    }
}
