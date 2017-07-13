/**
 *Autor: Lucas.
 * Data: 13/Julho/2017.
 * Objetivo: Prover comunicacao com o Werservice do sistema
 * equilibrio.
 *
*/
import { Subscriber } from 'rxjs/Subscriber';
import { Response } from '@angular/http';
import { Dto } from '../model/dto/Dto';

export class Controller extends Subscriber<Response> {

    //Resposta na integra, em texto da rquest realizada.
    private resposta: string;

    //Mapeia reposta retornada do WebService
    //Onde resposta.result guarda o conteudo
    //retornado pelo WebService em forma de String.
    // tslint:disable-next-line:member-ordering
    response: Response;

    /**Objeto que escurata a requisicao,
     * e devera ser passado pelo metodo
     * antes do controller escutar a requisicao.
     *
     * Objeto tambem reponsavel por mapear as entuais
     * mensagens de erro que possam vir a ocorrer.
    */
    private dto: Dto;

    /**Objeto que escutara a requisicao e
     * devera ser passado pelo metodo antes controller
     * escutar a requisicao.
    */
    private arrayDto: Array<Dto>;

    /***Escuta requisicao de array.
     * Objetivo marcar se o controller esta escutando
     * uma requisicao que retornara um array ou
     * nao.
    */
    private eArray: boolean;

    /***Token de sincronizacao que devera ser passado
     * em requisicoes do tipo POST.
    */
    private tokenSincronizacao: number;

    /**Variavel que marca se houve ou nao uma excecao
     * durante o processo de request, caso verdadeiro
     * houve uma excecao de negocio.
    */
    private erroNegocio: boolean = false;

    //Pega em ordem a resposta do WebService
    //Assim que a mesma chega a interface de rede
    //Do dispositivo cliente
    next(value: Response): void {
        /**Captura o reponse objeto do http.*/
        this.response = value;

        /**Pega string da reposta na integra.*/
        this.resposta = value.text();

        //VOLTA VALOR DA VARIAVEL DE ERRO DE NEGOCIO
        //PARA A POSICAO DE INICIALIZACAO A FIM DE SE AVALIAR
        //SE HOUVE ALGUMA EXCECAO A CADA REQUEST.
        this.erroNegocio = false;
        if (this.eArray) {
            /**Procura pela ocorrencia do caractere colchete
             * se o mesmo existir na string siginifica que
             * o objeto e um array e que portanto nao houve erro.
            */
            if (value.text().indexOf('[') > -1) {
                this.arrayDto = JSON.parse(this.response.text());
            } else {
                //Caso de erro a reposta do servidor nao
                //Foi um array como o esperado.
                //Guarda mensagem de erro em dto
                //E chama o metodo caso erro.
                this.dto = this.converteMensagemErroDto(value);
                //MARCA ERRO DE NEGOCIO PARA
                //A SOBRESCRITA DO CASO COMUNICAO COMPLETA
                this.erroNegocio = true;
            }
        } else {
            //ASSEGURA QUE A REPOSTA DO SERVIDOR
            //E DE FATO UM JSON.
            if (value.text().indexOf('{') > -1) {
                //DESERIALIZA O OBJETO DE REPOSTA
                //E O GUARDA EM DTO.
                this.dto = JSON.parse(value.text());

                /**Execoes de controle de acesso e de sincronizacao.*/
                if (this.dto.message !== null &&
                    this.dto.message !== undefined &&
                    this.dto.message !== '') {
                    this.dto.descricaoMensagem = this.dto.message;
                    //MARCA ERRO DE NEGOCIO PARA
                    //A SOBRESCRITA DO CASO COMUNICAO COMPLETA
                    this.erroNegocio = true;
                }
                /**Execoes de negocio.*/
                if (this.dto.tituloMensagem !== null &&
                    this.dto.tituloMensagem !== undefined &&
                    this.dto.tituloMensagem !== '' &&
                    this.dto.descricaoMensagem !== null &&
                    this.dto.descricaoMensagem !== undefined &&
                    this.dto.descricaoMensagem !== '') {

                    //VERIFICA SE O TITULO DA MENSAGEM DE ERRO
                    //CHEGOU CORRETAMENTE E SE SIM PASSA O VALOR AO OBJETO.
                    this.dto.tituloMensagem = this.dto.tituloMensagem;

                    //VERIFICA SE O CORPO DA MENSAGEM DE ERRO
                    //CHEGOU CORRETAMENTE E SE SIM PASSA O VALOR AO OBJETO.
                    this.dto.descricaoMensagem = this.dto.descricaoMensagem;

                    //MARCA ERRO DE NEGOCIO PARA
                    //A SOBRESCRITA DO CASO COMUNICAO COMPLETA
                    this.erroNegocio = true;
                }
            } //FIM DO IF
        } //FIM DO ELSE
    } //FIM DO ONEXT.


    //Mapeamento automatico do Angular2
    //O mesmo obeservar qualquer especie
    //De erro que possa acontecer na comunicao
    //Com servidor, sendo estes erros da aplicacao
    //ou falha na de rede.
    error(err?: any): void {
        this.casoErroRede();
    }

    //Mapeamento automatico do Angular2
    //Para quando se pede serviços em
    //O cliente deseja tomar alguma atitude
    //A comunicao estiver completada.
    //Util para fazer consultas no banco de dados,
    //Pois o metodo so e chamado quando todos os dados chegarem.
    complete(): void {
        if (this.erroNegocio) {
            this.casoErroNegocio();
        } else {
            this.casoComunicacaoCompleta();
        }
    }


    /**Metodo que converte as entuais mensagens de
     * erro em dto, apara que os clientes da classe possam
     * tratar o erro sobrerevendo o metodo casoErro()*/
    private converteMensagemErroDto(value: Response): Dto {
        let dto: Dto = JSON.parse(value.text());
        return dto;
    }

    /** Get do objeto dto, apara os casos onde a request esperava
     *  por um array mas a classe deve tratar as posssiveis mensagens
     *  de erro.
    */
    // tslint:disable-next-line:member-ordering
    public getDto(): Dto {
        return this.dto;
    }

    /** Metodo que reportna conteu na integra,
     *  util quando a reposta no servidor nao e um JSON.
    */
    public getResposta(): string {
        return this.resposta;
    }

    /*Caso de erro de rede ou erro interno no WebService(500)*/
    // tslint:disable-next-line:no-empty
    casoErroRede(): void {

    }

    /*Caso erro de negocio tenha ocorrido
    * quando o complete do angular for chamado
    * o mesmo chamara o casoErroNegocio() e
    * nao chamara o casoComunicacaoCompleta()*/
    // tslint:disable-next-line:no-empty
    casoErroNegocio(): void {

    }


    /*Caso de cofirmacao de comunicação completada com webservice*/
    // tslint:disable-next-line:no-empty
    casoComunicacaoCompleta(): void {

    }

    /** Metodo que devera ser chamado ao sobrescrever
     *  o comunicao casoComunicacaoCompleta afim de se
     *  haveriguar se houve ou nao alguma excecao.
    */
    public execaoNegocio(): boolean {
        return this.erroNegocio;
    }

    /** Recebe a instancia de um Objeto
     * que nao e um array para escutar requisicoes
     * do tipo GET
    */
    public requisicaoGetDto(dto: Dto): void {
        this.dto = dto;
        this.eArray = false;
    }

    /** Recebe a instancia de um objeto array
     * para escutar requisicoes do tipo GET.
    */
    public requisicaoGetArrayDto(arrayDto: Array<Dto>): void {
        this.eArray = true;
    }

    /*** Recebe a instancia de um obejeto que nao e um DTO
     *   para escutar requisicoes do tipo POST, onde devera ser passado
     *   o token de sincronizacao.
     */
    public requisicaoPostDto(dto: Dto, tokenSincronizacao: number): void {
        this.dto = dto;
        this.tokenSincronizacao = tokenSincronizacao;
        this.eArray = false;
    }

    /**Recebe a instancia de obejto que seja um array de DTO(s)
     * apara escutar requisicoes do tipo POST, onde tambem devera
     * ser passado o token de sincronizacao.
    */
    public requisicaoPostArrayDto(arrayDto: Array<Dto>, tokenSincronizacao: number): void {
        this.arrayDto = arrayDto;
        this.tokenSincronizacao = tokenSincronizacao;
        this.eArray = true;
    }
}
