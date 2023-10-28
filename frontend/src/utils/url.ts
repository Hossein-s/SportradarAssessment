export function url(path: string): string {
  return import.meta.env.VITE_APP_API_URL + path;
}
