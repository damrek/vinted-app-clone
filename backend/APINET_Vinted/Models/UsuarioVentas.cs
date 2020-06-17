using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APINET_Vinted.Models
{
    public class UsuarioVentas
    {
        public int Id { get; set; }
        public string Username { get; set; }
        public DateTime FechaRegistro { get; set; }
        public int TotalVentas { get; set; }
    }
}
