import { create } from "zustand";
import { UserInfo } from "@/lib/service/auth/auth-service";
import { AuthState } from "@/lib/types/auth/auth";

const useAuthStore = create<AuthState>((set) => ({
  userInfo: null as UserInfo | null,
  setUser: (userInfo: UserInfo) => set({ userInfo }),
  clearUser: () => set({ userInfo: null }),
}))

export default useAuthStore;