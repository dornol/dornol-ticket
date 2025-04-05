import { toast } from "sonner";

export const Dialog = {
  // alert: (message: string): Promise<void> => {
  //   return new Promise((resolve) => {
  //     alert(message);
  //     resolve();
  //   });
  // },
  alert: (message: string): Promise<void> => {
    return new Promise((resolve) => {
      toast(message);
      resolve();
    });
  },
};
