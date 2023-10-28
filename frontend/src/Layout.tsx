import { Link, Toolbar } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import CssBaseline from "@mui/material/CssBaseline";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Typography from "@mui/material/Typography";
import { Outlet, Link as RouterLink } from "react-router-dom";

const defaultTheme = createTheme({
  palette: { background: { default: "#F3F6F9" } },
});

export function Layout() {
  return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />

      <AppBar position="relative">
        <Toolbar>
          <Link
            component={RouterLink}
            variant="h6"
            color="inherit"
            to="/"
            sx={{ textDecoration: "none" }}
          >
            Scoreboard
          </Link>
        </Toolbar>
      </AppBar>
      <Outlet />
    </ThemeProvider>
  );
}
