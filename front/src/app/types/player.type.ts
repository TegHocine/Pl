export interface Player {
  id: number;
  playerName: string;
  nation: string;
  position: string;
  age: number;
  matchPlayed: number;
  starts: number;
  minutesPlayed: number;
  goals: number;
  assists: number;
  penaltiesScored: number;
  yellowCards: number;
  redCards: number;
  expectedGoals: number;
  expectedAssists: number;
  team: string;
}
