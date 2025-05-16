/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        extend: {
            fontFamily: {
                press: ['"Press Start 2P"', 'monospace'],
            },
        },
    },
    plugins: [],
};
