import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/auth': 'http://localhost:8080',
      '/channels': 'http://localhost:8080',
      '/activity': 'http://localhost:8080',
      '/hobby-test': 'http://localhost:8080',
      '/posts': 'http://localhost:8080',
      '/comments': 'http://localhost:8080',
      '/hearts': 'http://localhost:8080',
    }
  }
})
