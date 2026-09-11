// ===================================================================
//  PÁGINA DO CATÁLOGO
//
//  Os cards de cada planeta agora vêm prontos do servidor (Spring +
//  Thymeleaf, veja catalog.html e o CatalogController). Este arquivo
//  só liga a animação de cada card no seu <canvas>, lendo a cor e se
//  tem anéis a partir dos atributos "data-*" gerados pelo Thymeleaf.
// ===================================================================

const cards = document.querySelectorAll(".planet-card");

cards.forEach(function (card) {
  const canvas = card.querySelector(".planet-canvas");

  const planeta = {
    color: canvas.dataset.color,
    hasRings: canvas.dataset.rings === "true"
  };

  // A função "desenharPlanetaNoCard" está no arquivo planet-viewer.js.
  desenharPlanetaNoCard(canvas, planeta);
});
