class item {
     int id;
     int type;
     int posx;
     int posy;
     item() {
          id = 0;
          type = 0;
          posx = 0;
          posy = 0;
     }
     item(int id, int type, int posx, int posy) {
          this.id = id;
          this.type = type;
          this.posx = posx;
          this.posy = posy;
     }
     String display() {
          String t = "";
          switch (Math.abs(type)) {
          case 1:
               t = "pawn";
               break;
          case 2:
               t = "king"; break;
          case 3:
               t = "queen"; break;
          case 4:
               t = "rook"; break;
          case 5:
               t = "bishop"; break;
          case 6:
               t = "knight"; break;
          default:
               t = "tf"; break;
          }
          return "id: " + id + " type :" + t + " posx: " + posx +
              " posy : " + posy;
     }
}
