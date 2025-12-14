import iron from "../../assets/images/iron.png";
import bronze from "../../assets/images/bronze.png";
import silver from "../../assets/images/silver.png";
import gold from "../../assets/images/gold.png";
import platinum from "../../assets/images/platinum.png";
import diamond from "../../assets/images/master.png";
import master from "../../assets/images/grandmaster.png";

/* ===============================
   함수: 티어 이미지 결정
=============================== */
export function ImageOfTier(tier: string) {
  switch (tier) {
    case "iron":
      return iron;
    case "bronze":
      return bronze;
    case "silver":
      return silver;
    case "gold":
      return gold;
    case "platinum":
      return platinum;
    case "diamond":
      return diamond;
    case "master":
      return master;
    default:
      return iron; // 기본값
  }
}
