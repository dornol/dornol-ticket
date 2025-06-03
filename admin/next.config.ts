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
        protocol: 'http',
        hostname: 'localhost',
        port: '8080',
        pathname: `${process.env.NEXT_PUBLIC_API_PATH}/files/**`,
      }
    ],
  }
};

export default nextConfig;
