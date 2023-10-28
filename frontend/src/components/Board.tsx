import AddIcon from "@mui/icons-material/Add";
import {
  Card,
  CardContent,
  CircularProgress,
  Container,
  Fab,
  Typography,
} from "@mui/material";
import { Box } from "@mui/system";
import { useState } from "react";
import { useQuery } from "react-query";
import { useNavigate } from "react-router-dom";
import { MatchDTO } from "../types/MatchDTO";
import { url } from "../utils/url";
import { CreateMatchDialog } from "./CreateMatchDialog";

export function Board() {
  const { isLoading, isError, data, refetch } = useQuery<MatchDTO[]>(
    "/matches",
    () => fetch(url("/matches")).then((res) => res.json())
  );
  const navigate = useNavigate();
  const [dialogOpen, setDialogOpen] = useState(false);

  const wrap = (content: JSX.Element | JSX.Element[]) => {
    return (
      <Container maxWidth="sm">
        <Typography variant="h4" mt="30px" textAlign="center">
          MATCHES
        </Typography>

        {content}

        <CreateMatchDialog
          open={dialogOpen}
          onClose={() => setDialogOpen(false)}
          onCreate={() => refetch()}
        />

        <Fab
          variant="extended"
          color="primary"
          sx={{ position: "fixed", right: "30px", bottom: "30px" }}
          onClick={() => setDialogOpen(true)}
        >
          <AddIcon sx={{ mr: 1 }} />
          Add Match
        </Fab>
      </Container>
    );
  };

  if (isLoading) {
    return wrap(
      <Box sx={{ display: "flex", justifyContent: "center", mt: "50px" }}>
        <CircularProgress size="50px" />
      </Box>
    );
  }

  if (isError) {
    return wrap(
      <Box sx={{ display: "flex", justifyContent: "center", mt: "50px" }}>
        <Typography variant="h5">Error occurred while fetching data</Typography>
      </Box>
    );
  }

  if (data?.length === 0) {
    return wrap(
      <Box sx={{ display: "flex", justifyContent: "center", mt: "50px" }}>
        <Typography variant="h5">
          No match yet, add using button on the corner ;)
        </Typography>
      </Box>
    );
  }

  return wrap(
    data!.map((match) => (
      <Card
        key={match.id}
        aria-label="scoreboard"
        elevation={2}
        sx={{ mt: "20px", cursor: "pointer" }}
        onClick={() => navigate(`/matches/${match.id}`)}
      >
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
              <Typography variant="h5">{match.homeTeam}</Typography>
              <Typography variant="h4">{match.homeScore}</Typography>
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
              <Typography variant="h4">{match.awayScore}</Typography>
              <Typography variant="h5">{match.awayTeam}</Typography>
            </Box>
          </Box>
        </CardContent>
      </Card>
    ))
  );
}
