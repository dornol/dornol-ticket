export default {
  alert: async (message: string) => {
    return new Promise<void>((resolve) => {
      alert(message);
      return resolve();
    })
  }
}