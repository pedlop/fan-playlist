import { Component } from '@angular/core';

@Component({
    selector: 'app-ranking',
    templateUrl: './ranking.component.html',
    styleUrls: ['./ranking.component.css']
})
export class RankingComponent {

    public rankings: Array<Raking> = new Array<Raking>();

    constructor() {
        this.rankings.push(
            {
                colocacao:1,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:2,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:3,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:4,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:5,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:6,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:7,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            },
            {
                colocacao:8,
                musica:'Vidinha de Balada',
                album:'Vidinha de Balada',
                artista:'Henrique e Juliano',
                fotoArtista:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg',
                fotoAlbum:'https://s2.vagalume.com/henrique-e-juliano/images/henrique-e-juliano.jpg'
            }
        );
    }
}

class Raking {
    colocacao: number;
    musica: string;
    album: string;
    artista: string;
    fotoArtista: string;
    fotoAlbum: string;
}
