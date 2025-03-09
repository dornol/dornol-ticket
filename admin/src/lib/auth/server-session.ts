import { getServerSession } from "next-auth";
import { authOptions } from "@/app/api/auth/[...nextauth]/auth-options";

export default async function getDefaultServerSession() {
  return await getServerSession(authOptions);
}