import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  output: 'standalone',
  experimental: {
    turbo: {
      minify: true,
    },
  },
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 's3.dornol.dev',
        port: '',
        pathname: "/**",
        search: ''
      }
    ],
  }
};

export default nextConfig;
