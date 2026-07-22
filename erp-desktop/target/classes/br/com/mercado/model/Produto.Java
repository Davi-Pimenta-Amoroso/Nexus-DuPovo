package br.com.mercado.model.Produto;

public class Produto {

    public class Produto {
        private long id;
        private String nome;
        private double preco;
        private int quantidade;
        private String categoria;

        public Produto() {}

        public Produto(long id, String nome, double preco, int quantidade, String categoria) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
            this.categoria = categoria;
        }

        public long getId() { return id; }
        public void setId(long id) { this.id = id; }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public double getPreco() { return preco; }
        public void setPreco(double preco) { this.preco = preco; }
        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }

    }


}
