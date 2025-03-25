export const Dialog = {
  alert: (message: string): Promise<void> => {
    return new Promise((resolve) => {
      alert(message);
      resolve();
    });
  },
};
