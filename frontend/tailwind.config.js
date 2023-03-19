/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [],
  theme: {
    screens: {
      'xs': '320px',
      'sm': '640px',
      'md': '768px',
      'lg': '1024px',
      'xl': '1280px',
      '2xl': '1440px',
      '3xl': '1600px',
      '4xl': '1920px',
      '5xl': '2560px'
    },
    extend: {
      colors: {
        custom_orange: {
          100: "#fae9d7",
          200: "#f6d4af",
          300: "#f1be88",
          400: "#eda960",
          500: "#e89338",
          600: "#ba762d",
          700: "#8b5822",
          800: "#5d3b16",
          900: "#2e1d0b"
        },
        custom_blue: {
          100: "#cde3eb",
          200: "#9bc7d7",
          300: "#6aacc4",
          400: "#3890b0",
          500: "#06749c",
          600: "#055d7d",
          700: "#04465e",
          800: "#022e3e",
          900: "#01171f"
        },
        custom_white: {
          100: "#fffbf8",
          200: "#fef7f0",
          300: "#fef4e9",
          400: "#fdf0e1",
          500: "#fdecda",
          600: "#cabdae",
          700: "#988e83",
          800: "#655e57",
          900: "#332f2c"
        },
        light_mint_green: {
          100: "#e7f6ea",
          200: "#cfeed6",
          300: "#b8e5c1",
          400: "#a0ddad",
          500: "#88d498",
          600: "#6daa7a",
          700: "#527f5b",
          800: "#36553d",
          900: "#1b2a1e"
        },
        green_ocean: {
          100: "#d1e9e2",
          200: "#a3d4c5",
          300: "#76bea9",
          400: "#48a98c",
          500: "#1a936f",
          600: "#157659",
          700: "#105843",
          800: "#0a3b2c",
          900: "#051d16"
        },
        dark_blue: {
          100: "#cfdbdf",
          200: "#a0b7bf",
          300: "#70939f",
          400: "#416f7f",
          500: "#114b5f",
          600: "#0e3c4c",
          700: "#0a2d39",
          800: "#071e26",
          900: "#030f13"
        },
        crimson: {
          100: "#f9d9db",
          200: "#f3b4b7",
          300: "#ee8e93",
          400: "#e8696f",
          500: "#e2434b",
          600: "#b5363c",
          700: "#88282d",
          800: "#5a1b1e",
          900: "#2d0d0f"
        },
      },
      // fontFamily: {
      //   'cormorant': ['Cormorant Garamond', 'sans-serif']
      // }
    },
  },
  plugins: [],
}
