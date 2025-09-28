# DogQuiz

Please clone and have a look.

Some afterword:
1) Completed a MVP over an afternoon break so here are some thing I would have done after this:
   - Write Unit tests on View and Data layer
   - Write UI test to test both positive and negative flows (Happy path, no wifi, api error)

2) Some improvements in retrospect:
   - Move the NavHost and all its composable routes into a dedicated, reusable Composable function, rather than keeping it inside MainActivity.
   - If we have reusable compose components we can move it out into mod-ui and reuse from there
   - Offline first state, caching the data locally for user to use first to avoid delays or unhappy (eg:network) scenarios. With some mechanisms to sync/merge local and remote changes

Thanks for the challenge, it was a good break! :)

Expected flow:
<video src="https://github.com/user-attachments/assets/642f55f4-24b2-437a-b0ae-6729a30c634f" controls playsinline muted loop>
</video>

Unhappy flow (No wifi Connection)
<video src="https://github.com/user-attachments/assets/cac52140-0a6e-44be-99e5-53c264b1c70e" controls playsinline muted loop>
</video>
