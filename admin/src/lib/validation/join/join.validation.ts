import { z } from "zod";
import joinService from "@/lib/service/join-service";

export const joinFormScheme = z.object({
  username: z.string()
    .min(4, { message: "아이디는 4글자 이상으로 입력해 주세요.", })
    .max(16, { message: "아이디는 16글자 이내로 입력해 주세요." })
    .regex(/^[a-zA-Z0-9]{4,16}$/, { message: "아이디는 영문 또는 숫자만 가능합니다." })
    .refine(async (username) => {
      return !!username && await joinService.checkUsername(username);
    }, { message: "이미 사용중인 아이디 입니다." }),
  password: z.string()
    .min(4, { message: "비밀번호는 4글자 이상으로 입력해 주세요." })
    .max(32, { message: "비밀번호는 32글자 이내로 입력해 주세요." }),
  name: z.string()
    .min(2, { message: "이름은 2글자 이상으로 입력해 주세요.", })
    .max(10, { message: "이름은 10글자 이내로 입력해 주세요.", })
    .regex(/[가-힣]{2,10}/, { message: "이름은 한글로 입력해 주세요.", }),
  email: z.string()
    .email({ message: "이메을 형식을 확인해 주세요." }),
  phone: z.string()
    .regex(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, { message: "올바른 전화번호를 입력해 주세요." }),
  company: z.object({
    businessNumber: z.string()
      .min(10, {message: "정확한 사업자번호를 입력해 주세요."})
      .max(10, {message: "정확한 사업자번호를 입력해 주세요."})
      .regex(/^[0-9]{10}$/, {message: "정확한 사업자번호를 입력해 주세요."}),
    name: z.string()
      .min(2, {message: "사업자명은 2~20 자 까지 입력 가능합니다."})
      .max(20, {message: "사업자명은 2~20 자 까지 입력 가능합니다."})
      .regex(/^[a-zA-Z0-9가-힣()\s]{2,20}$/, {message:"사업자명은 2~20 자 까지 입력 가능합니다."})
  })
});

export const joinFormDefaultValues = {
  username: "",
  password: "",
  name: "",
  email: "",
  phone: "",
  company: {
    businessNumber: "",
    name: "",
  }
}