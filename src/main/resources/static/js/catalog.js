// ===================================================================
//  PÁGINA DO CATÁLOGO
//
//  Aqui montamos um "card" para cada planeta da lista PLANETS
//  (que vem do arquivo planets-data.js) e colocamos na página.
// ===================================================================

// Pega a área da página onde os cards vão aparecer.
const grade = document.getElementById("catalogGrid");

// Passa por cada planeta da lista, um de cada vez.
for (let i = 0; i < PLANETS.length; i++) {
  const planeta = PLANETS[i];

  // Cria um elemento <article> que será o card.
  const card = document.createElement("article");
  card.className = "planet-card";

  // Escreve o conteúdo do card em HTML.
  // O sinal + junta os pedaços de texto com os dados do planeta.
  card.innerHTML =
    '<canvas class="planet-canvas"></canvas>' +
    '<h3>' + planeta.name + '</h3>' +
    '<p class="planet-desc">' + planeta.description + '</p>' +
    '<dl class="planet-stats">' +
      '<div><dt>Massa</dt><dd>' + planeta.mass + '</dd></div>' +
      '<div><dt>Distância do Sol</dt><dd>' + planeta.distance + '</dd></div>' +
      '<div><dt>Temperatura média</dt><dd>' + planeta.temperature + '</dd></div>' +
      '<div><dt>Duração do dia</dt><dd>' + planeta.dayLength + '</dd></div>' +
    '</dl>';

  // Coloca o card dentro da página.
  grade.appendChild(card);

  // Pega o <canvas> desse card e liga a animação do planeta nele.
  // A função "desenharPlanetaNoCard" está no arquivo planet-viewer.js.
  const canvas = card.querySelector(".planet-canvas");
  desenharPlanetaNoCard(canvas, planeta);
}
