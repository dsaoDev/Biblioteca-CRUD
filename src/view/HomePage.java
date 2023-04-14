package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.LivroDAO;
import enums.Categoria;
import model.Livro;

public class HomePage {
	
	public void HomePageBiblioteca()  {
		Livro livro = null;
		Scanner sc = new Scanner(System.in);
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		LivroDAO rps = null;
		boolean rodando = true;
		
		while(rodando) {
			try {
			System.out.println("\t Bem vindo");
			System.out.println("[1] -> Cadastrar Livro no Sistema");
			System.out.println("[2] -> Buscar Livro via ID");
			System.out.println("[3] -> Listar Livros cadastrados");
			System.out.println("[4] -> Atualizar Dados de Livros /OBS:SE O ID PASSADO COMO PARAMETRO N�O EXISTIR , OS DADOS INFORMADOS SER�O INSERIDOS NO SISTEMA");
			System.out.println("[5] -> Deletar Livros do Sistema /OBS:TODOS OS DADOS SER�O PERDIDOS");
			System.out.println("[0] -> Encerrar Programa");
			System.out.print("Informe sua op��o: ");
			int opcao = sc.nextInt();
			sc.nextLine();
			
			switch(opcao) {
			case 1:
				 livro = new Livro();
				System.out.println("\t # ABA DE CADASTRO DE LIVROS #");
				
				System.out.print("Titulo: ");
				livro.setTitulo(sc.nextLine());
				
				System.out.print("Autor: ");
				livro.setAutor(sc.nextLine());
				
				System.out.print("ISBN: ");
				livro.setIsbn(sc.nextLine());
				
				
				System.out.print("Categoria: ");
				String categoria = sc.nextLine();
				Categoria category = Categoria.valueOf(categoria);
				livro.setCategoria(category);
				
				//setando dataAtual
				livro.setData(sdf.format(data));
				
				System.out.print("N�mero de Paginas: ");
				livro.setNumPaginas(sc.nextInt());
				//cleaning buffer
				sc.nextLine();
				
				System.out.print("Valor: ");
				livro.setValor(sc.nextDouble());
				
				boolean inseriu ;
				rps = new LivroDAO();
				inseriu = rps.Insert(livro);
				
				if(inseriu) {
					System.out.println("Inseriu");
				}else {
					System.out.println("Falha na Inser��o");
				}
				
				break;
				
			case 2:
				System.out.println("\t ABA DE BUSCA POR LIVRO VIA ID");
				System.out.print("Informe o Id do Livro que voc� deseja realizar a busca: ");
				int id= sc.nextInt();
				sc.nextLine();
				livro = new Livro();
				rps = new LivroDAO();
				livro = rps.searchById(id);
				
				if(livro != null) {
					System.out.println("Livro encontrado com Sucesso !!");
					System.out.print(livro.toString());
				}else {
					System.out.println("Livro n�o encontrado no sistema");
				}
				break;
				
			case 3:
				System.out.println("\t ABA DE LISTAGEM");
				ArrayList<Livro> livros = new ArrayList<>();
				rps = new LivroDAO();
				livros = rps.list();
				if(livros.size() == 0) {
					System.out.println("A LISTA EST� VAZIA");
				}else {
					System.out.println("\t LISTAGEM ");
				for (Livro livroTs : livros) {
					System.out.println(livroTs.toString());
				}
				}
				break;
				
			case 4:
				livro = new Livro();
				System.out.println("\t ABA DE ATUALIZA��O DE CADASTRO");
				System.out.print("Informe o Id do Livro que voc� deseja realizar a atualiza��o: ");
				livro.setId(sc.nextInt());
				sc.nextLine();
				System.out.print("Infome o Novo titulo: ");
				livro.setTitulo(sc.nextLine());
				System.out.print("Informe o Novo autor: ");
				livro.setAutor(sc.nextLine());
				System.out.print("Informe o novo ISBN: ");
				livro.setIsbn(sc.nextLine());
				System.out.print("Informe o novo Valor: ");
				livro.setValor(sc.nextDouble());
				livro.setData(sdf.format(data));
				System.out.print("Informe a nova Categoria: ");
				categoria = sc.nextLine();
				category = Categoria.valueOf(categoria);
				livro.setCategoria(category);
				System.out.print("Informe o n�mero de Paginas: ");
				livro.setNumPaginas(sc.nextInt());
				sc.nextLine();
				rps = new LivroDAO();
				boolean atualizou = rps.update(livro);
				if(atualizou) {
					System.out.println("ATUALIZOU");
				}else {
					System.out.println("FALHA AO ATUALIZAR, INSERINDO NOVO DADO NO SISTEMA");
				}
				
				break;
				
			case 5:
				System.out.println("ABA DE DELETE *TODOS OS DADOS SER�O PERDIDOS* ");
				System.out.print("informe o Id do livro que voc� deseja remover: ");
				id = sc.nextInt();
				rps = new LivroDAO();
				livro = rps.searchById(id);
				boolean deletou = rps.delete(id);
				if(deletou == false) {
					System.out.println("Deletou");
				}else {
					System.out.println("Falhou");
				}
				break;
				
			case 0:
				System.out.println("Progama Finalizado At� a proxima... ");
				rodando  = false;
				break;
				
			default:
			System.out.println("OPCAO INVALIDA INFORME UMA OP��O DE 0-5");
			break;
			}
			}catch(Exception e) {
				System.out.println("Invalid Input... please Try again");
				e.printStackTrace();
				continue;
			}
			
		}
	}
	
}
