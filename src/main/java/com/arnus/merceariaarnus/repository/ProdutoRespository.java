package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.dto.view.ProdutoView;
import com.arnus.merceariaarnus.dto.view.ProdutosMiasVendidos;
import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRespository extends JpaRepository<ProdutoModel, Integer> {
    Optional<ProdutoModel> findByIdAndStatusTrue(Integer id);

    @Query(value = "SELECT p.nome AS produto, cp.nome AS categoria, SUM(ip.qtd) AS totalVendido\n" +
            "FROM tb_inste_pedido ip\n" +
            "INNER JOIN tb_produto p ON ip.produto_id = p.id\n" +
            "INNER JOIN tb_categoria cp ON p.categoria_id  = cp.id \n" +
            "GROUP BY p.id, cp.id \n" +
            "ORDER BY totalVendido DESC",nativeQuery = true)
    List<ProdutosMiasVendidos> consultarProdutosMaisVendios();

    @Query("SELECT p.id as id, p.nome as nome, p.preco as preco, p.categoriaModel.nome as categoriaProduto FROM ProdutoModel p")
    List<ProdutoView> buscarProdutos();
}
