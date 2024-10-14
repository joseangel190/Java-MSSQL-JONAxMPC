declare module NodeJS {
    interface Module {
      hot?: {
        accept(dependency: string, callback: () => void): void;
      };
    }
  }
  