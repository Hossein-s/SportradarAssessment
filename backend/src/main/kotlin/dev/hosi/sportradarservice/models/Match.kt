package dev.hosi.sportradarservice.models

class Match(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
) {
    var homeScore = 0
        private set
    var awayScore = 0
        private set

    fun updateScore(homeScore: Int, awayScore: Int) {
        this.homeScore = homeScore
        this.awayScore = awayScore
    }
}
