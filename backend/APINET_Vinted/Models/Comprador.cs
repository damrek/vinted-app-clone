using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace APINET_Vinted.Models
{
    public class Comprador
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int Id { get; set; }

        [StringLength(maximumLength: 60)]
        public string DirecEnvio { get; set; }

        [StringLength(maximumLength: 30)]
        public string TarjetaCuenta { get; set; }

        public virtual ICollection<Pedido> Pedidos { get; set; }

    }
}
