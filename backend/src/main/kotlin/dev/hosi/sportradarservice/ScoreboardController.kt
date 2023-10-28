package dev.hosi.sportradarservice

import dev.hosi.sportradarservice.dtos.CreateMatchInput
import dev.hosi.sportradarservice.dtos.MatchOutput
import dev.hosi.sportradarservice.dtos.UpdateMatchInput
import dev.hosi.sportradarservice.models.Scoreboard
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("matches")
class ScoreboardController(private val scoreBoard: Scoreboard) {
    @PostMapping
    fun createMatch(@RequestBody match: CreateMatchInput): MatchOutput {
        return scoreBoard.createMatch(match.homeTeam, match.awayTeam)
    }

    @GetMapping("{id}")
    fun getMatch(@PathVariable id: Int): MatchOutput {
        return scoreBoard.getMatch(id)
    }

    @PatchMapping("{id}")
    fun updateMatch(@PathVariable id: Int, @RequestBody update: UpdateMatchInput): MatchOutput {
        return scoreBoard.updateMatch(id, update.homeScore, update.awayScore)
    }

    @DeleteMapping("{id}")
    fun finishMatch(@PathVariable id: Int) {
        scoreBoard.finishMatch(id)
    }

    @GetMapping
    fun getMatches(): List<MatchOutput> {
        return scoreBoard.getMatches()
    }
}
