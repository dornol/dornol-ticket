import { toast } from "sonner";

export const Dialog = {
  alert: (message: string): Promise<void> => {
    return new Promise((resolve) => {
      toast(message);
      resolve();
    });
  },
  confirm: (message: string): Promise<boolean> => {
    return new Promise((resolve) => {
      const result = confirm(message);
      resolve(result);
    })
  }
};
