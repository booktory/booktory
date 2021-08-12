module.exports = {
  devServer: {
    proxy: {
      "^/": {
        target: "https://i5a607.p.ssafy.io",
        changeOrigin: true,
        logLevel: "debug",
      },
    },
  },
};
