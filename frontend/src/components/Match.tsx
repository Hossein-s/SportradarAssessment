import { LoadingButton } from "@mui/lab";
import {
  Box,
  Card,
  CardActions,
  CardContent,
  CircularProgress,
  Container,
  Input,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { useMutation, useQuery } from "react-query";
import { useParams } from "react-router-dom";
import { MatchDTO } from "../types/MatchDTO";
import { url } from "../utils/url";

export function Match() {
  const { id } = useParams();
  const [homeScore, setHomeScore] = useState(0);
  const [awayScore, setAwayScore] = useState(0);
  const { isLoading, isError, data } = useQuery<MatchDTO>(
    ["match", id],
    () => fetch(url(`/matches/${id}`)).then((res) => res.json()),
    {
      refetchOnWindowFocus: false,
      refetchInterval: false,
      onSuccess(res) {
        setHomeScore(res.homeScore);
        setAwayScore(res.awayScore);
      },
    }
  );
  const { mutate, isLoading: isUpdating } = useMutation(() =>
    fetch(url(`/matches/${id}`), {
      method: "PATCH",
      body: JSON.stringify({ homeScore, awayScore }),
      headers: { "Content-Type": "application/json" },
    }).then((res) => res.json())
  );

  if (isLoading) {
    return (
      <Box sx={{ display: "flex", justifyContent: "center", mt: "50px" }}>
        <CircularProgress size="50px" />
      </Box>
    );
  }

  if (isError) {
    return (
      <Box sx={{ display: "flex", justifyContent: "center", mt: "50px" }}>
        <Typography variant="h5">Error occurred while fetching data</Typography>
      </Box>
    );
  }

  return (
    <Container maxWidth="sm" sx={{ mt: "60px" }}>
      <Card aria-label="match" elevation={2}>
        <CardContent>
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              width: "100%",
              gap: "20px",
            }}
          >
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                gap: "20px",
              }}
            >
              <Typography variant="h5">{data!.homeTeam}</Typography>
              <Input
                type="number"
                sx={{ fontSize: "1.5rem", width: "60px", textAlign: "center" }}
                inputProps={{ style: { textAlign: "center" } }}
                value={homeScore}
                onChange={(e) => setHomeScore(Number(e.target.value))}
              />
            </Box>
            <Typography variant="h4">&mdash;</Typography>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                gap: "20px",
              }}
            >
              <Input
                type="number"
                sx={{ fontSize: "1.5rem", width: "60px" }}
                inputProps={{ style: { textAlign: "center" } }}
                value={awayScore}
                onChange={(e) => setAwayScore(Number(e.target.value))}
              />
              <Typography variant="h5">{data!.awayTeam}</Typography>
            </Box>
          </Box>
        </CardContent>
        <CardActions sx={{ justifyContent: "center", mt: "10px", pb: "20px" }}>
          <LoadingButton
            variant="contained"
            sx={{ width: "200px" }}
            size="small"
            color="primary"
            loading={isUpdating}
            onClick={() => mutate()}
          >
            SAVE
          </LoadingButton>
        </CardActions>
      </Card>
    </Container>
  );
}
