import {
  Box,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
} from "@mui/material";
import Button from "@mui/material/Button";
import { useState } from "react";
import { useMutation } from "react-query";
import { url } from "../utils/url";
import { LoadingButton } from "@mui/lab";

export interface Props {
  open: boolean;
  onClose: () => void;
  onCreate: () => void;
}

export function CreateMatchDialog({ open, onClose, onCreate }: Props) {
  const [homeTeam, setHomeTeam] = useState("");
  const [awayTeam, setAwayTeam] = useState("");
  const { mutate, isLoading } = useMutation(
    () =>
      fetch(url("/matches"), {
        method: "POST",
        body: JSON.stringify({ homeTeam, awayTeam }),
        headers: { "Content-Type": "application/json" },
      }).then((res) => res.json()),
    {
      onSuccess: () => {
        onCreate();
        onClose();
      },
    }
  );

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Create Match</DialogTitle>
      <DialogContent>
        <Box sx={{ display: "flex", gap: "10px" }}>
          <TextField
            autoFocus
            margin="dense"
            label="Home Team"
            name="home_team"
            type="text"
            fullWidth
            variant="standard"
            value={homeTeam}
            onChange={(e) => setHomeTeam(e.target.value)}
          />
          <TextField
            autoFocus
            margin="dense"
            label="Away Team"
            name="away_team"
            type="text"
            fullWidth
            variant="standard"
            value={awayTeam}
            onChange={(e) => setAwayTeam(e.target.value)}
          />
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancel</Button>
        <LoadingButton loading={isLoading} onClick={() => mutate()}>
          Create
        </LoadingButton>
      </DialogActions>
    </Dialog>
  );
}
