import { defineConfig } from 'vitest/config';

export default defineConfig({
    test: {
        environment: 'jsdom',                 // DOM-like testing
        globals: true,                        // allow describe/it/expect without imports
        setupFiles: ['test/setup.ts'],        // run once before tests
        css: true,                            // let Vitest process CSS imports
        include: ['test/**/*.{test,spec}.ts?(x)'],   // where the tests live
        exclude: ['node_modules', 'dist'],    // usual excludes
        coverage: {
            provider: 'v8',                     // `@vitest/coverage-v8`
            reporter: ['text', 'html', 'lcov'],
            reportsDirectory: 'coverage',
            all: false,                         // set true if you want coverage to include untested files
            include: ['src/**/*.{ts,tsx}'],
            exclude: ['**/*.d.ts', 'src/**/*.types.ts'],
        },
    },
});
