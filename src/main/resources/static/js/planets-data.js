// ===================================================================
//  LISTA DE PLANETAS
//
//  Este arquivo só guarda dados. Não faz nada sozinho.
//  "PLANETS" é uma lista (array). Cada item { ... } é um planeta
//  com suas informações. Os outros arquivos JS leem esta lista.
//
//  Campos de cada planeta:
//    name        -> nome que aparece na tela
//    color       -> cor do planeta no desenho (formato "#rrggbb")
//    size        -> tamanho do círculo do planeta, em pixels
//    hasRings    -> true só para Saturno (desenha um anel)
//    description -> frase curta mostrada no catálogo
//    mass, distance, temperature, dayLength -> textos informativos
// ===================================================================

const PLANETS = [
  {
    name: "Mercúrio",
    color: "#9c9c94",
    size: 4,
    description: "O menor planeta e o mais rápido, torrado pela proximidade com o Sol.",
    mass: "3,30 × 10^23 kg",
    distance: "57,9 milhões de km",
    temperature: "167 °C (média)",
    dayLength: "58,6 dias terrestres"
  },
  {
    name: "Vênus",
    color: "#e0c16c",
    size: 7,
    description: "Efeito estufa descontrolado: é mais quente que Mercúrio mesmo estando mais longe.",
    mass: "4,87 × 10^24 kg",
    distance: "108,2 milhões de km",
    temperature: "464 °C (média)",
    dayLength: "243 dias terrestres"
  },
  {
    name: "Terra",
    color: "#3a86ff",
    size: 7,
    description: "O único mundo conhecido com oceanos líquidos e vida na superfície.",
    mass: "5,97 × 10^24 kg",
    distance: "149,6 milhões de km",
    temperature: "15 °C (média)",
    dayLength: "24 horas"
  },
  {
    name: "Marte",
    color: "#c1440e",
    size: 5,
    description: "O Planeta Vermelho, com o maior vulcão e o maior cânion do Sistema Solar.",
    mass: "6,42 × 10^23 kg",
    distance: "227,9 milhões de km",
    temperature: "-65 °C (média)",
    dayLength: "24,6 horas"
  },
  {
    name: "Júpiter",
    color: "#d8ca9d",
    size: 16,
    description: "Um gigante gasoso tão grande que caberiam todos os outros planetas dentro dele.",
    mass: "1,90 × 10^27 kg",
    distance: "778,5 milhões de km",
    temperature: "-110 °C (média)",
    dayLength: "9,9 horas"
  },
  {
    name: "Saturno",
    color: "#ead6b8",
    size: 14,
    hasRings: true,
    description: "Famoso pelos anéis brilhantes feitos de gelo e rocha.",
    mass: "5,68 × 10^26 kg",
    distance: "1,43 bilhão de km",
    temperature: "-140 °C (média)",
    dayLength: "10,7 horas"
  },
  {
    name: "Urano",
    color: "#9fe3f0",
    size: 10,
    description: "Um gigante de gelo que gira quase deitado de lado.",
    mass: "8,68 × 10^25 kg",
    distance: "2,87 bilhões de km",
    temperature: "-195 °C (média)",
    dayLength: "17,2 horas"
  },
  {
    name: "Netuno",
    color: "#3b5bdb",
    size: 10,
    description: "O mundo mais ventoso, com tempestades acima de 2.000 km/h.",
    mass: "1,02 × 10^26 kg",
    distance: "4,50 bilhões de km",
    temperature: "-200 °C (média)",
    dayLength: "16,1 horas"
  }
];
