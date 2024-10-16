import { PaginationResponse } from '@/types/global.type';
import { Player } from '@/types/player.type';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, ReplaySubject, tap } from 'rxjs';

type PagedPlayers = PaginationResponse<Player>;

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private http = inject(HttpClient);
  public _players: ReplaySubject<PagedPlayers> =
    new ReplaySubject<PagedPlayers>(1);

  baseUrl = 'api/v1/player';
  get players$(): Observable<PagedPlayers> {
    return this._players.asObservable();
  }

  getPlayersByNation(
    nation = '',
    page = 0,
    size = 10
  ): Observable<PagedPlayers> {
    return this.http
      .get<PagedPlayers>(`${this.baseUrl}/nation`, {
        params: {
          nation,
          page: page ? page - 1 : page,
          size,
        },
      })
      .pipe(
        tap((players: PagedPlayers) => {
          this._players.next(players);
        })
      );
  }

  getPlayersByTeam(team = '', page = 0, size = 10): Observable<PagedPlayers> {
    return this.http
      .get<PagedPlayers>(`${this.baseUrl}/team`, {
        params: {
          team,
          page: page ? page - 1 : page,
          size,
        },
      })
      .pipe(
        tap((players: PagedPlayers) => {
          this._players.next(players);
        })
      );
  }

  getPlayersByPosition(
    position = '',
    page = 0,
    size = 10
  ): Observable<PagedPlayers> {
    return this.http
      .get<PagedPlayers>(`${this.baseUrl}/position`, {
        params: {
          position,
          page: page ? page - 1 : page,
          size,
        },
      })
      .pipe(
        tap((players: PagedPlayers) => {
          this._players.next(players);
        })
      );
  }
}
