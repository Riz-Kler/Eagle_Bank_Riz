import '@testing-library/jest-dom';  // extra matchers
import 'whatwg-fetch';               // fetch/Headers/Request/Response polyfill

// If you’re using MSW, you can start/stop per-test in test/server.ts,
// no need to start here unless you want global handlers.
