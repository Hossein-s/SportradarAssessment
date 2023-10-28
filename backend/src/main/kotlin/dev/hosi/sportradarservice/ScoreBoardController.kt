package dev.hosi.sportradarservice

import dev.hosi.sportradarservice.dtos.CreateMatchInput
import dev.hosi.sportradarservice.dtos.MatchOutput
import dev.hosi.sportradarservice.dtos.UpdateMatchInput
import dev.hosi.sportradarservice.models.ScoreBoard
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("matches")
class ScoreBoardController(private val scoreBoard: ScoreBoard) {
    @PostMapping
    fun createMatch(@RequestBody match: CreateMatchInput): MatchOutput {
        return scoreBoard.createMatch(match.homeTeam, match.awayTeam)
    }

    @PatchMapping(":id")
    fun updateMatch(@RequestParam id: Int, @RequestBody update: UpdateMatchInput) {
        scoreBoard.updateMatch(id, update.homeScore, update.awayScore)
    }

    @DeleteMapping(":id")
    fun finishMatch(@RequestParam id: Int) {
        scoreBoard.finishMatch(id)
    }

    @GetMapping
    fun getMatches(): List<MatchOutput> {
        return scoreBoard.getMatches()
    }
}
